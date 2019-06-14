/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MSellCardMedalDeleteDialogComponent } from 'app/entities/m-sell-card-medal/m-sell-card-medal-delete-dialog.component';
import { MSellCardMedalService } from 'app/entities/m-sell-card-medal/m-sell-card-medal.service';

describe('Component Tests', () => {
  describe('MSellCardMedal Management Delete Component', () => {
    let comp: MSellCardMedalDeleteDialogComponent;
    let fixture: ComponentFixture<MSellCardMedalDeleteDialogComponent>;
    let service: MSellCardMedalService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MSellCardMedalDeleteDialogComponent]
      })
        .overrideTemplate(MSellCardMedalDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MSellCardMedalDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MSellCardMedalService);
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
