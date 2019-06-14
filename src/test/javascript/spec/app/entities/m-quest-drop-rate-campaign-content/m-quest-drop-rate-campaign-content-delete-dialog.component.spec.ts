/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestDropRateCampaignContentDeleteDialogComponent } from 'app/entities/m-quest-drop-rate-campaign-content/m-quest-drop-rate-campaign-content-delete-dialog.component';
import { MQuestDropRateCampaignContentService } from 'app/entities/m-quest-drop-rate-campaign-content/m-quest-drop-rate-campaign-content.service';

describe('Component Tests', () => {
  describe('MQuestDropRateCampaignContent Management Delete Component', () => {
    let comp: MQuestDropRateCampaignContentDeleteDialogComponent;
    let fixture: ComponentFixture<MQuestDropRateCampaignContentDeleteDialogComponent>;
    let service: MQuestDropRateCampaignContentService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestDropRateCampaignContentDeleteDialogComponent]
      })
        .overrideTemplate(MQuestDropRateCampaignContentDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestDropRateCampaignContentDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestDropRateCampaignContentService);
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
