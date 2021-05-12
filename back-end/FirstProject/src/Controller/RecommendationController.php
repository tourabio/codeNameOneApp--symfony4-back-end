<?php

namespace App\Controller;

use App\Entity\Recommendation;
use App\Form\RecommendationType;
use App\Repository\RecommendationRepository;
use App\Repository\UserRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/recommendation")
 */
class RecommendationController extends AbstractController
{
    /**
     * @Route("/", name="recommendation_index", methods={"GET"})
     */
    public function index(RecommendationRepository $recommendationRepository): Response
    {
        return $this->render('recommendation/index.html.twig', [
            'recommendations' => $recommendationRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="recommendation_new", methods={"GET","POST"})
     */
    public function new(Request $request , UserRepository $userRepository): Response
    {
        $recommendation = new Recommendation();
        $recommendation->setIdutilisateur($userRepository->find('12345661'));
        $recommendation->setDate(new \DateTime());
        $form = $this->createForm(RecommendationType::class, $recommendation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($recommendation);
            $entityManager->flush();

            return $this->redirectToRoute('student_recommend');
        }

        return $this->render('recommendation/new.html.twig', [
            'recommendation' => $recommendation,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="recommendation_show", methods={"GET"})
     */
    public function show(Recommendation $recommendation): Response
    {
        return $this->render('recommendation/show.html.twig', [
            'recommendation' => $recommendation,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="recommendation_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Recommendation $recommendation): Response
    {
        $form = $this->createForm(RecommendationType::class, $recommendation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('recommendation_index');
        }

        return $this->render('recommendation/edit.html.twig', [
            'recommendation' => $recommendation,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="recommendation_delete", methods={"POST"})
     */
    public function delete(Request $request, Recommendation $recommendation): Response
    {
        if ($this->isCsrfTokenValid('delete'.$recommendation->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($recommendation);
            $entityManager->flush();
        }

        return $this->redirectToRoute('recommendation_index');
    }
}
