/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MApRecoveryItemDeleteDialogComponent } from 'app/entities/m-ap-recovery-item/m-ap-recovery-item-delete-dialog.component';
import { MApRecoveryItemService } from 'app/entities/m-ap-recovery-item/m-ap-recovery-item.service';

describe('Component Tests', () => {
  describe('MApRecoveryItem Management Delete Component', () => {
    let comp: MApRecoveryItemDeleteDialogComponent;
    let fixture: ComponentFixture<MApRecoveryItemDeleteDialogComponent>;
    let service: MApRecoveryItemService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MApRecoveryItemDeleteDialogComponent]
      })
        .overrideTemplate(MApRecoveryItemDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MApRecoveryItemDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MApRecoveryItemService);
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
