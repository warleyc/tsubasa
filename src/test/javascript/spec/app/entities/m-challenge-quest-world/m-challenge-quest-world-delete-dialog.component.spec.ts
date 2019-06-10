/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MChallengeQuestWorldDeleteDialogComponent } from 'app/entities/m-challenge-quest-world/m-challenge-quest-world-delete-dialog.component';
import { MChallengeQuestWorldService } from 'app/entities/m-challenge-quest-world/m-challenge-quest-world.service';

describe('Component Tests', () => {
  describe('MChallengeQuestWorld Management Delete Component', () => {
    let comp: MChallengeQuestWorldDeleteDialogComponent;
    let fixture: ComponentFixture<MChallengeQuestWorldDeleteDialogComponent>;
    let service: MChallengeQuestWorldService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MChallengeQuestWorldDeleteDialogComponent]
      })
        .overrideTemplate(MChallengeQuestWorldDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MChallengeQuestWorldDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MChallengeQuestWorldService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
