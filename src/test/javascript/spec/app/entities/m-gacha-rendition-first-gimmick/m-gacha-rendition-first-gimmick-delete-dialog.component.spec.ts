/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionFirstGimmickDeleteDialogComponent } from 'app/entities/m-gacha-rendition-first-gimmick/m-gacha-rendition-first-gimmick-delete-dialog.component';
import { MGachaRenditionFirstGimmickService } from 'app/entities/m-gacha-rendition-first-gimmick/m-gacha-rendition-first-gimmick.service';

describe('Component Tests', () => {
  describe('MGachaRenditionFirstGimmick Management Delete Component', () => {
    let comp: MGachaRenditionFirstGimmickDeleteDialogComponent;
    let fixture: ComponentFixture<MGachaRenditionFirstGimmickDeleteDialogComponent>;
    let service: MGachaRenditionFirstGimmickService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionFirstGimmickDeleteDialogComponent]
      })
        .overrideTemplate(MGachaRenditionFirstGimmickDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionFirstGimmickDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionFirstGimmickService);
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
