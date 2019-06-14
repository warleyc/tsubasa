/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestStageRewardDeleteDialogComponent } from 'app/entities/m-quest-stage-reward/m-quest-stage-reward-delete-dialog.component';
import { MQuestStageRewardService } from 'app/entities/m-quest-stage-reward/m-quest-stage-reward.service';

describe('Component Tests', () => {
  describe('MQuestStageReward Management Delete Component', () => {
    let comp: MQuestStageRewardDeleteDialogComponent;
    let fixture: ComponentFixture<MQuestStageRewardDeleteDialogComponent>;
    let service: MQuestStageRewardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestStageRewardDeleteDialogComponent]
      })
        .overrideTemplate(MQuestStageRewardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestStageRewardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestStageRewardService);
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
