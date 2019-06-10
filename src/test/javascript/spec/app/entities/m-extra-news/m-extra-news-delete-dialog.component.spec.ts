/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TsubasaTestModule } from '../../../test.module';
import { MExtraNewsDeleteDialogComponent } from 'app/entities/m-extra-news/m-extra-news-delete-dialog.component';
import { MExtraNewsService } from 'app/entities/m-extra-news/m-extra-news.service';

describe('Component Tests', () => {
  describe('MExtraNews Management Delete Component', () => {
    let comp: MExtraNewsDeleteDialogComponent;
    let fixture: ComponentFixture<MExtraNewsDeleteDialogComponent>;
    let service: MExtraNewsService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MExtraNewsDeleteDialogComponent]
      })
        .overrideTemplate(MExtraNewsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MExtraNewsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MExtraNewsService);
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
