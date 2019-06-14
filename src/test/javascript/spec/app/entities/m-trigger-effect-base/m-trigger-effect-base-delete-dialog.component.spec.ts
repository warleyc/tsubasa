/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MTriggerEffectBaseDeleteDialogComponent } from 'app/entities/m-trigger-effect-base/m-trigger-effect-base-delete-dialog.component';
import { MTriggerEffectBaseService } from 'app/entities/m-trigger-effect-base/m-trigger-effect-base.service';

describe('Component Tests', () => {
  describe('MTriggerEffectBase Management Delete Component', () => {
    let comp: MTriggerEffectBaseDeleteDialogComponent;
    let fixture: ComponentFixture<MTriggerEffectBaseDeleteDialogComponent>;
    let service: MTriggerEffectBaseService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTriggerEffectBaseDeleteDialogComponent]
      })
        .overrideTemplate(MTriggerEffectBaseDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTriggerEffectBaseDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTriggerEffectBaseService);
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
