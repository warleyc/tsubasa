/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MStoreReviewUrlUpdateComponent } from 'app/entities/m-store-review-url/m-store-review-url-update.component';
import { MStoreReviewUrlService } from 'app/entities/m-store-review-url/m-store-review-url.service';
import { MStoreReviewUrl } from 'app/shared/model/m-store-review-url.model';

describe('Component Tests', () => {
  describe('MStoreReviewUrl Management Update Component', () => {
    let comp: MStoreReviewUrlUpdateComponent;
    let fixture: ComponentFixture<MStoreReviewUrlUpdateComponent>;
    let service: MStoreReviewUrlService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MStoreReviewUrlUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MStoreReviewUrlUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MStoreReviewUrlUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MStoreReviewUrlService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MStoreReviewUrl(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MStoreReviewUrl();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
