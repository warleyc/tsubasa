/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MEventTitleEffectDeleteDialogComponent } from 'app/entities/m-event-title-effect/m-event-title-effect-delete-dialog.component';
import { MEventTitleEffectService } from 'app/entities/m-event-title-effect/m-event-title-effect.service';

describe('Component Tests', () => {
  describe('MEventTitleEffect Management Delete Component', () => {
    let comp: MEventTitleEffectDeleteDialogComponent;
    let fixture: ComponentFixture<MEventTitleEffectDeleteDialogComponent>;
    let service: MEventTitleEffectService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MEventTitleEffectDeleteDialogComponent]
      })
        .overrideTemplate(MEventTitleEffectDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MEventTitleEffectDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MEventTitleEffectService);
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
