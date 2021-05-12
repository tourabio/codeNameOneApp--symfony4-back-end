<?php

namespace App\Controller;

use App\Entity\Reclamation;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\ReclamationRepository;
use App\Repository\UserRepository;
use Swift_Mailer;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Twilio\Rest\Client;

/**
 * @Route("/api/reclamation")
 */

class ApiReclamationController extends AbstractController
{
    /**
     * @Route("/", name="api_reclamation")
     */
    public function index(ReclamationRepository $reclamationRepository, Request $request): Response
    {
        $reclamations = $reclamationRepository->findAll();

        foreach ($reclamations as $rec) {
            $date = $rec->getDate();
            $rec->setDateFormatted($date->format('Y-m-d H:i:s'));
        }


        $encoders = array(new XmlEncoder(), new JsonEncoder());
        $normalizers = array(new ObjectNormalizer());
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceLimit(2);
        // Add Circular reference handler
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers, $encoders);
        $formatted = $serializer->normalize($reclamations);
        return new JsonResponse($formatted);
    }



    /**
     * @Route("/delete/{id}", name="reclamation_deleteApi", methods={"POST"})
     */
    public function delete(Request $request, Reclamation $reclamation): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->remove($reclamation);
        $entityManager->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize("reclamation supprimer");
        return new JsonResponse($formatted);
    }


    /**
     * @Route("/add", name="reclamation_add_api", methods={"GET","POST"})
     */
    public function new(Request $request, UserRepository $userRepository): Response
    {
        $reclamation = new Reclamation();
        // get user connected nt a static one
        $reclamation->setComment($request->get('comment'));
        $reclamation->setSujet($request->get('sujet'));
        $reclamation->setDate(new \DateTime());
        $reclamation->setIdutilisateur($userRepository->find($request->get('idUser')));
        $reclamation->setEtat('en cours');

        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($reclamation);
        $entityManager->flush();
        /*$sid = "AC7ab9cc0c4225cc97724ef0070abc67a8";
            $token = "47b6c35f23847f4fbd8cb70c01051cb0";
            $client = new Client($sid, $token);
            $client->messages->create(
                '+216 92 701 035',
                array(
                    'from' => '+16194314835',
                    'body' => "vous avez une reclamation !"
                )
            );*/

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($reclamation);
        return new JsonResponse($formatted);
    }

    /**
     * @Route("/edit", name="reclamation_editApi", methods={"GET","POST"})
     */
    public function edit(Request $request, UserRepository $userRepository, ReclamationRepository $rr): Response
    {
        $idReclamation = $request->get('id');
        $em = $this->getDoctrine()->getManager();
        $reclamation  =  $rr->find($idReclamation);
        $reclamation->setComment($request->get('comment'));
        $reclamation->setSujet($request->get('sujet'));
        $reclamation->setDate(new \DateTime());
        $reclamation->setIdutilisateur($userRepository->find($request->get('idUser')));
        $reclamation->setEtat('en cours');
        $em->persist($reclamation);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize("reclamation modifier");
        return new JsonResponse($formatted);
    }

    /**
     * @Route("/search", name="api_reclamation_search")
     */
    public function serach(ReclamationRepository $reclamationRepository, Request $request): Response
    {

        $sujet = $request->get('sujet');
        $reclamations = $reclamationRepository->findBySujet($sujet);

        foreach ($reclamations as $rec) {
            $date = $rec->getDate();
            $rec->setDateFormatted($date->format('Y-m-d H:i:s'));
        }
        $encoders = array(new XmlEncoder(), new JsonEncoder());
        $normalizers = array(new ObjectNormalizer());
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceLimit(2);
        // Add Circular reference handler
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers, $encoders);
        $formatted = $serializer->normalize($reclamations);
        return new JsonResponse($formatted);
    }


    /**
     * @Route("/sendMail", name="api_reclamation_sendMail", methods={"GET","POST"})
     */
    public function sendEmail(MailerInterface $mailer, UserRepository $userRepository, Request $request): Response
    {

        $user = $userRepository->find($request->get('idUser'));
        $email = (new Email())
            ->from('khalil.tourabi10@gmail.com')
            ->to($user->getEmail())
            ->subject('Reclamation confirmation send')
            ->text('we receive your reclamation , and we will see what we can do . thank you');
            //->html('<p>See Twig integration for better HTML integration!</p>');

        $mailer->send($email);

       /* $message = (new \Swift_Message('Reclamation confirmation send'))
            ->setFrom('khalil.tourabi10@gmail.com')
            ->setTo($user->getEmail())
            ->setBody(
                'we receive your reclamation , and we will see what we can do . thank you'
            );*/

        



        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize("email sent");
        return new JsonResponse($formatted);
    }
}
