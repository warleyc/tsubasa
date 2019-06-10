/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MChatSystemMessageDetailComponent } from 'app/entities/m-chat-system-message/m-chat-system-message-detail.component';
import { MChatSystemMessage } from 'app/shared/model/m-chat-system-message.model';

describe('Component Tests', () => {
  describe('MChatSystemMessage Management Detail Component', () => {
    let comp: MChatSystemMessageDetailComponent;
    let fixture: ComponentFixture<MChatSystemMessageDetailComponent>;
    const route = ({ data: of({ mChatSystemMessage: new MChatSystemMessage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MChatSystemMessageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MChatSystemMessageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MChatSystemMessageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mChatSystemMessage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
