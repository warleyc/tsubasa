/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildTopBannerUpdateComponent } from 'app/entities/m-guild-top-banner/m-guild-top-banner-update.component';
import { MGuildTopBannerService } from 'app/entities/m-guild-top-banner/m-guild-top-banner.service';
import { MGuildTopBanner } from 'app/shared/model/m-guild-top-banner.model';

describe('Component Tests', () => {
  describe('MGuildTopBanner Management Update Component', () => {
    let comp: MGuildTopBannerUpdateComponent;
    let fixture: ComponentFixture<MGuildTopBannerUpdateComponent>;
    let service: MGuildTopBannerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildTopBannerUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGuildTopBannerUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGuildTopBannerUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGuildTopBannerService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGuildTopBanner(123);
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
        const entity = new MGuildTopBanner();
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
