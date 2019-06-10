/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCardThumbnailAssetsUpdateComponent } from 'app/entities/m-card-thumbnail-assets/m-card-thumbnail-assets-update.component';
import { MCardThumbnailAssetsService } from 'app/entities/m-card-thumbnail-assets/m-card-thumbnail-assets.service';
import { MCardThumbnailAssets } from 'app/shared/model/m-card-thumbnail-assets.model';

describe('Component Tests', () => {
  describe('MCardThumbnailAssets Management Update Component', () => {
    let comp: MCardThumbnailAssetsUpdateComponent;
    let fixture: ComponentFixture<MCardThumbnailAssetsUpdateComponent>;
    let service: MCardThumbnailAssetsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCardThumbnailAssetsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MCardThumbnailAssetsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MCardThumbnailAssetsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCardThumbnailAssetsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MCardThumbnailAssets(123);
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
        const entity = new MCardThumbnailAssets();
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
