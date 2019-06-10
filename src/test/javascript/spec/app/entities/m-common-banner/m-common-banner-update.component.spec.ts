/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCommonBannerUpdateComponent } from 'app/entities/m-common-banner/m-common-banner-update.component';
import { MCommonBannerService } from 'app/entities/m-common-banner/m-common-banner.service';
import { MCommonBanner } from 'app/shared/model/m-common-banner.model';

describe('Component Tests', () => {
  describe('MCommonBanner Management Update Component', () => {
    let comp: MCommonBannerUpdateComponent;
    let fixture: ComponentFixture<MCommonBannerUpdateComponent>;
    let service: MCommonBannerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCommonBannerUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MCommonBannerUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MCommonBannerUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCommonBannerService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MCommonBanner(123);
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
        const entity = new MCommonBanner();
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
