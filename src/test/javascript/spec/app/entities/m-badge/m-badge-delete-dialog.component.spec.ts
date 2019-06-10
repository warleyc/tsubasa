/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MBadgeDeleteDialogComponent } from 'app/entities/m-badge/m-badge-delete-dialog.component';
import { MBadgeService } from 'app/entities/m-badge/m-badge.service';

describe('Component Tests', () => {
  describe('MBadge Management Delete Component', () => {
    let comp: MBadgeDeleteDialogComponent;
    let fixture: ComponentFixture<MBadgeDeleteDialogComponent>;
    let service: MBadgeService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MBadgeDeleteDialogComponent]
      })
        .overrideTemplate(MBadgeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MBadgeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MBadgeService);
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
