/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MHomeBannerUpdateComponent } from 'app/entities/m-home-banner/m-home-banner-update.component';
import { MHomeBannerService } from 'app/entities/m-home-banner/m-home-banner.service';
import { MHomeBanner } from 'app/shared/model/m-home-banner.model';

describe('Component Tests', () => {
  describe('MHomeBanner Management Update Component', () => {
    let comp: MHomeBannerUpdateComponent;
    let fixture: ComponentFixture<MHomeBannerUpdateComponent>;
    let service: MHomeBannerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MHomeBannerUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MHomeBannerUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MHomeBannerUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MHomeBannerService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MHomeBanner(123);
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
        const entity = new MHomeBanner();
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
