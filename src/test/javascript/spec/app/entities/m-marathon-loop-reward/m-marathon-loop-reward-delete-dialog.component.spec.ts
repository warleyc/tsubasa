/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonLoopRewardDeleteDialogComponent } from 'app/entities/m-marathon-loop-reward/m-marathon-loop-reward-delete-dialog.component';
import { MMarathonLoopRewardService } from 'app/entities/m-marathon-loop-reward/m-marathon-loop-reward.service';

describe('Component Tests', () => {
  describe('MMarathonLoopReward Management Delete Component', () => {
    let comp: MMarathonLoopRewardDeleteDialogComponent;
    let fixture: ComponentFixture<MMarathonLoopRewardDeleteDialogComponent>;
    let service: MMarathonLoopRewardService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonLoopRewardDeleteDialogComponent]
      })
        .overrideTemplate(MMarathonLoopRewardDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMarathonLoopRewardDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMarathonLoopRewardService);
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
