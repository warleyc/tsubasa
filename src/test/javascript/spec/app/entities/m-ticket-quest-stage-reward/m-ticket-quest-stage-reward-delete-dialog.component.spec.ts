/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTicketQuestStageRewardDeleteDialogComponent } from 'app/entities/m-ticket-quest-stage-reward/m-ticket-quest-stage-reward-delete-dialog.component';
import { MTicketQuestStageRewardService } from 'app/entities/m-ticket-quest-stage-reward/m-ticket-quest-stage-reward.service';

describe('Component Tests', () => {
  describe('MTicketQuestStageReward Management Delete Component', () => {
    let comp: MTicketQuestStageRewardDeleteDialogComponent;
    let fixture: ComponentFixture<MTicketQuestStageRewardDeleteDialogComponent>;
    let service: MTicketQuestStageRewardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTicketQuestStageRewardDeleteDialogComponent]
      })
        .overrideTemplate(MTicketQuestStageRewardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTicketQuestStageRewardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTicketQuestStageRewardService);
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
